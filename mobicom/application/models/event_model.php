<?php

class Event_Model extends CI_Model {

    private $columns = array(

        'id' => '`events`.`id` as event_id',
        'title' => '`events`.`title` as title',
        'description' => '`events`.`description` as description',
        'location' => '`events`.`location` as location',
        'date start' => "DATE_FORMAT(`events`.`date_start`, '%b %d, %Y') as date_start",
        'time start' => "TIME_FORMAT(`events`.`time_start`, '%H:%i') as time_start",
        'date end' => "DATE_FORMAT(`events`.`date_end`, '%b %d, %Y') as date_end",
        'time end' => "TIME_FORMAT(`events`.`time_end`, '%H:%i') as time_end",
        'latitude' => 'x(`events`.`geolocation`) as latitude',
        'longitude' => 'y(`events`.`geolocation`) as longitude'

    );
    private $date_today = "'Nov 28, 2014'";

    public function __construct(){

        parent::__construct();
        $this->load->database();

    }

    public function create_event($data){

        $title = $data['title'];
        $location = $data['location'];
        $description = $data['description'];
        $date_start = $data['date_start'];
        $time_start = $data['time_start'];
        $date_end = $data['date_end'];
        $time_end = $data['time_end'];
        $latitude = $data['latitude'];
        $longitude = $data['longitude'];

        $user = $this->ion_auth->user()->row();
        $this->db->query("

            INSERT INTO

                `plan8`.`events`
                (
                    `id`,
                    `title`,
                    `description`,
                    `date_start`,
                    `time_start`,
                    `date_end`,
                    `time_end`,
                    `location`,
                    `geolocation`,
                    `user_id`,
                    `date_created`,
                    `time_created`
                )

                VALUES
                (
                    NULL,
                    '".$title."',
                    '".$description."',
                    STR_TO_DATE('".$date_start."', '%b %d, %Y'),
                    '".$time_start."',
                    STR_TO_DATE('".$date_end."', '%b %d, %Y'),
                    '".$time_end."',
                    '".$location."',
                    GeomFromText('POINT(".$latitude." ".$longitude.")',0),
                    ".$user->id.",
                    CURDATE(),
                    CURTIME()
                )

        ");

        $this->load->view('register_view', $data);

    }

    public function invite_self($event_id){

        $user = $this->ion_auth->user()->row();

        $this->db->query("

            INSERT INTO

                `plan8`.`invites` (

                    `user_id`,
                    `event_id`,
                    `status`,
                    `geolocation`

                )

                VALUES (

                    ".$user->id.",
                    '".$event_id."',
                    '1',
                    GeomFromText(NULL)

                )

        ");

    }

    public function invite_friends_to_event($event_id, $friends_ids){

        $friend_ids = explode(',', $friends_ids);
        $event_id = $event_id;

        if($friends_ids != ''){

            foreach($friend_ids as $friend_id){

                $this->db->query("

                    INSERT INTO

                        `plan8`.`invites` (

                            `user_id`,
                            `event_id`,
                            `status`,
                            `geolocation`

                        )

                        VALUES (

                            '".$friend_id."',
                            '".$event_id."',
                            '0',
                            GeomFromText(NULL)

                        )

                ");

            }

        }

    }

    public function edit_event_info($data){

        $user = $this->ion_auth->user()->row();

        $event_id = $data['event_id'];
        $title = $data['title'];
        $location = $data['location'];
        $description = $data['description'];
        $date_start = $data['date_start'];
        $time_start = $data['time_start'];
        $date_end = $data['date_end'];
        $time_end = $data['time_end'];
        $latitude = $data['latitude'];
        $longitude = $data['longitude'];

        $result['date_start'] = 'valid';
        $result['time_start'] = 'valid';
        $result['date_end'] = 'valid';
        $result['time_end'] = 'valid';
        $valid_create_event = true;

        $this->db->query("

            UPDATE
                `events`

            SET
                `events`.`title` = '".$title."',
                `events`.`description` = '".$description."',
                `events`.`date_start` = STR_TO_DATE('".$date_start."', '%b %d, %Y'),
                `events`.`time_start` = '".$time_start."',
                `events`.`date_end` = STR_TO_DATE('".$date_end."', '%b %d, %Y'),
                `events`.`time_end` = '".$time_end."',
                `events`.`location` = '".$location."',
                `events`.`geolocation` = GeomFromText('POINT(".$latitude." ".$longitude.")',0)

            WHERE
                `events`.`id` = ".$event_id." and
                `events`.`user_id` = ".$user->id."

        ");

    }

    public function get_event_info($event_id){

        $user = $this->ion_auth->user()->row();

        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['description'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end'].",
                ".$this->columns['latitude'].",
                ".$this->columns['longitude'].",
                IF(`users`.`id` = ".$user->id.", 'you', CONCAT(`users`.`first_name`, ' ',`users`.`last_name`)) AS `created_by`,
                IF(`users`.`id` = ".$user->id.", 'true', 'false') AS `is_user`

            FROM
                `events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `events`.`id` = ".$event_id."

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_declined_events(){

        $user = $this->ion_auth->user()->row();

        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end'].",
                ".$this->columns['latitude'].",
                ".$this->columns['longitude']."

            FROM
                (
                    SELECT
                        `invites`.`event_id`,
                        `invites`.`status`
                    FROM
                        `invites`
                    WHERE
                        `invites`.`user_id` = ".$user->id."

                ) AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                (
                    `invites`.`status` = 2 or
                    (
                        DATE(`events`.`date_end`) <= CURDATE() and
                        TIME(`events`.`time_end`) <= CURTIME() and
                        `invites`.`status` = 0
                    )
                )

            ORDER by
                `events`.`date_start` DESC,
                `events`.`date_end` DESC,
                `events`.`time_start` DESC,
                `events`.`time_end` DESC

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_finished_events(){

        $user = $this->ion_auth->user()->row();
        //".$user->id."
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end'].",
                ".$this->columns['latitude'].",
                ".$this->columns['longitude']."

            FROM
                (
                    SELECT
                        `invites`.`event_id`,
                        `invites`.`status`
                    FROM
                        `invites`
                    WHERE
                        `invites`.`user_id` = ".$user->id."

                ) AS `invites`

                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 1 and
                (
                    DATE(`events`.`date_end`) < CURDATE() or
                    (
                        DATE(`events`.`date_end`) = CURDATE() and
                        TIME(`events`.`time_end`) < CURTIME()
                    )
                )

            ORDER by
                `events`.`date_start` DESC,
                `events`.`date_end` DESC,
                `events`.`time_start` DESC,
                `events`.`time_end` DESC

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_invited_events(){

        $user = $this->ion_auth->user()->row();

        //".$user->id."
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end'].",
                ".$this->columns['latitude'].",
                ".$this->columns['longitude']."

            FROM
                (
                    SELECT
                        `invites`.`event_id`,
                        `invites`.`status`
                    FROM
                        `invites`
                    WHERE
                        `invites`.`user_id` = ".$user->id."

                )
                AS `invites`

                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 0 and
                (
                    DATE(`events`.`date_end`) > CURDATE() or
                    (
                        DATE(`events`.`date_end`) = CURDATE() and
                        TIME(`events`.`time_end`) >= CURTIME()
                    )
                )

            ORDER by
                `events`.`date_start` ASC,
                `events`.`date_end` ASC,
                `events`.`time_start` ASC,
                `events`.`time_end` ASC

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_upcoming_events(){

        $user = $this->ion_auth->user()->row();
        //".$user->id."
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end'].",
                ".$this->columns['latitude'].",
                ".$this->columns['longitude']."

            FROM
                (
                    SELECT
                        `invites`.`event_id`,
                        `invites`.`status`
                    FROM
                        `invites`
                    WHERE
                        `invites`.`user_id` = ".$user->id."

                ) AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 1 and
                (
                    DATE(`events`.`date_end`) > CURDATE() or
                    (
                        DATE(`events`.`date_end`) = CURDATE() and
                        TIME(`events`.`time_end`) >= CURTIME()
                    )
                )

            ORDER by
                `events`.`date_start` ASC,
                `events`.`date_end` ASC,
                `events`.`time_start` ASC,
                `events`.`time_end` ASC

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_event_attendees($event_id){

        $query = $this->db->query("

            SELECT
                `users`.`pic`,
                `users`.`first_name`,
                `users`.`last_name`,
                x(`invites`.`geolocation`) as latitude,
                y(`invites`.`geolocation`) as longitude

            FROM
                `users`, `invites`

            WHERE
                `users`.`id` = `invites`.`user_id` and
                `invites`.`event_id` = '".$event_id."' and
                `invites`.`status` = 1

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_friends_not_yet_invited($event_id){

        $user = $this->ion_auth->user()->row();

        $query = $this->db->query("

            SELECT
                `users`.`id`,
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
                        LEFT JOIN `invites`
                        ON `invites`.`user_id` = `friends`.`user_b_id` and
                        `invites`.`event_id` = ".$event_id."

                    WHERE
                        `friends`.`user_a_id` = ".$user->id." and
                        `invites`.`user_id` is NULL

                ) as `friends`

            WHERE
                `users`.`id` = `friends`.`id` and
                `users`.`id` != ".$user->id."

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function get_attendee_location($attendee_id, $event_id){

        $attendee_id = 2;
        $event_id = 1;

        $query = $this->db->query("

            SELECT
                x(`invites`.`geolocation`) as 'latitude',
                y(`invites`.`geolocation`) as 'longitude'

            FROM
                `invites`

            WHERE
                `invites`.`event_id` = ".$event_id." and
                `invites`.`user_id` = ".$attendee_id."

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function set_invitation_status($event_id, $invitation_status){

        $user = $this->ion_auth->user()->row();
        $this->db->query("

            UPDATE
                `invites`

            SET
                `status` = ".$invitation_status."

            WHERE
                `user_id` = ".$user->id." and
                `event_id` = ".$event_id."


        ");

    }


}

?>