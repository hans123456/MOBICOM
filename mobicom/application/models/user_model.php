<?php

class User_Model extends CI_Model {

    public function __construct(){

        parent::__construct();
        $this->load->database();

    }

    public function edit_profile($data){

        $id = $data['id'];
        $first_name = $data['first name'];
        $last_name = $data['last name'];

    }

    public function upload_image($data){

        $id = $data['id'];
        $image_directory = $data['image directory'];

    }

    public function get_profile_by_username($username){

        $query = $this->db->query("

            SELECT
                `users`.`id`,
                `users`.`pic`,
                `users`.`username`,
                `users`.`first_name`,
                `users`.`last_name`

            FROM
                `users`

            WHERE
                `users`.`username` = '".$username."'

        ");

        $info = '';

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

}

?>
