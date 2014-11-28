<?php

class Friend_Model extends CI_Model {

	public function __construct(){

		parent::__construct();
		$this->load->database();

	}

	public function get_friends(){

		$user = $this->ion_auth->user()->row();
		//".$user->id."
		$query = $this->db->query("

			SELECT
				`friends`.`id`,
				`users`.`unique_id`,
				`users`.`pic`,
				`users`.`first_name`,
				`users`.`last_name`

			FROM
				`users`,

				(

					SELECT
						`friends`.`user_b_id` as 'id'

					FROM
						`friends`

					WHERE
						`friends`.`user_a_id` = 1 and
						`friends`.`status` = 1

				) as `friends`

			WHERE
				`users`.`id` = `friends`.`id`

		");

		$info = "";

		if ($query->num_rows() > 0)
		{
			$info = json_encode($query->result_array());
		}

		$query->free_result();

		return $info;

	}

	public function get_friend_requests(){

		$user = $this->ion_auth->user()->row();
		// ".$user->id."
		$query = $this->db->query("

			SELECT
				`users`.`id`,
				`users`.`unique_id`,
				`users`.`pic`,
				`users`.`first_name`,
				`users`.`last_name`

			FROM
				`users`, `friends`

			WHERE
				`friends`.`user_b_id` = 1 and
				`users`.`id` = `friends`.`user_a_id` and
				`friends`.`status` = 0

		");

		$info = "";

		if ($query->num_rows() > 0)
		{
			$info = json_encode($query->result_array());
		}

		$query->free_result();

		return $info;

	}

	public function get_sent_friend_requests(){

		$user = $this->ion_auth->user()->row();
		// ".$user->id."
		$query = $this->db->query("

			SELECT
				`users`.`id`,
				`users`.`unique_id`,
				`users`.`pic`,
				`users`.`first_name`,
				`users`.`last_name`

			FROM
				`users`, `friends`

			WHERE
				`friends`.`user_a_id` = 1 and
				`users`.`id` = `friends`.`user_b_id` and
				`friends`.`status` = 0

		");

		$info = "";

		if ($query->num_rows() > 0)
		{
			$info = json_encode($query->result_array());
		}

		$query->free_result();

		return $info;

	}

	public function accept_friend_request($friend_id){

		$user = $this->ion_auth->user()->row();

		$this->db->trans_start();

			$query = $this->db->query("

				INSERT INTO
				    `friends`
				    (
				        `user_a_id`,
				        `user_b_id`,
				        `status`
				    )

				    VALUES
				    (
				        ".$user->id.",
				        ".$friend_id.",
				        1
				    )

				ON DUPLICATE KEY
				    UPDATE
				        `user_a_id` = ".$user->id.",
				        `user_b_id` = ".$friend_id.",
				        `status` = 1

				;

			");

			$query = $this->db->query("

				INSERT INTO
				    `friends`
				    (
				        `user_a_id`,
				        `user_b_id`,
				        `status`
				    )

				    VALUES
				    (
				        ".$friend_id.",
				        ".$user->id.",
				        1
				    )

				ON DUPLICATE KEY
				    UPDATE
				        `user_a_id` = ".$friend_id.",
				        `user_b_id` = ".$user->id.",
				        `status` = 1

				;

			");

		$this->db->trans_complete();

	}

	public function unfriend($friend_id){

		$user = $this->ion_auth->user()->row();

		$this->db->trans_start();

			$this->db->query("

				DELETE
					FROM
				    `friends`

				WHERE
			        `user_a_id` = ".$user->id." and
			        `user_b_id` = ".$friend_id."

				;

			");

			$this->db->query("

				DELETE
					FROM
				    `friends`

				WHERE
			        `user_a_id` = ".$friend_id." and
			        `user_b_id` = ".$user->id."

				;

			");

		$this->db->trans_complete();

	}

	public function delete_friend_request($friend_id){

		$user = $this->ion_auth->user()->row();

		$this->db->trans_start();

			$this->db->query("

				DELETE
					FROM
				    	`friends`

				WHERE
			        (
				       	`user_a_id` = ".$user->id." and
				        `user_b_id` = ".$friend_id."
				    )

				;

			");

			$this->db->query("

				DELETE
					FROM
				    	`friends`

				WHERE
			        {
						`user_a_id` = ".$friend_id." and
				        `user_b_id` = ".$user->id."
					}

				;

			");

		$this->db->trans_complete();

	}

}

?>