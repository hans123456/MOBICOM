<?php

class Event_Model extends CI_Model {

    private $columns =  array(

        'id' => '`events`.`id`',
        'title' => '`events`.`title`',
        'description' => '`events`.`description`',
        'location' => '`events`.`location`',
        'date start' => "DATE_FORMAT(`events`.`date_start`, '%b %d, %Y') as date_start",
        'time start' => "TIME_FORMAT(`events`.`time_start`, '%h:%i %p') as time_start",
        'date end' => "DATE_FORMAT(`events`.`date_end`, '%b %d, %Y') as date_end",
        'time end' => "TIME_FORMAT(`events`.`time_end`, '%h:%i %p') as time_end",
        'latitude' => 'x(`events`.`geolocation`) as latitude',
        'longitude' => 'y(`events`.`geolocation`) as longitude'

    );

    public function __construct(){

        parent::__construct();
        $this->load->database();

    }

    public function create_event($data){

        $title = $data['title'];
        $location = $data['location'];
        $description = $data['description'];
        $date_start = $data['date start'];
        $time_start = $data['time start'];
        $date_end = $data['date end'];
        $time_end = $data['time end'];
        $latitude = $data['latitude'];
        $longitude = $data['longitude'];

        // edit date and time created
        $user = $this->ion_auth->user()->row();
        $query = $this->db->query("

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
                    '".$date_start."',
                    '".$time_start."',
                    '".$date_end."',
                    '".$time_end."',
                    '".$location."',
                    GeomFromText('POINT(".$latitude." ".$longitude.")',0),
                    ".$user->id.",
                    '2014-11-17',
                    '22:42:00'
                )

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function edit_event_info($data){

        $title = $data['title'];
        $description = $data['description'];
        $date_start = $data['date start'];
        $time_start = $data['time start'];
        $date_end = $data['date end'];
        $time_end = $data['time end'];
        $latitude = $data['latitude'];
        $longitude = $data['longitude'];
        $location = $data['location'];
        $event_id = $data['event id'];

        $query = $this->db->query("

            UPDATE
                `events`

            SET
                `events`.`title` = '".$title."',
                `events`.`description` = '".$description."',
                `events`.`date_start` = '".$date_start."',
                `events`.`time_start` = '".$time_start."',
                `events`.`date_end` = '".$date_end."',
                `events`.`time_end` = '".$time_end."',
                `events`.`location` = '".$location."',
                `events`.`geolocation` = GeomFromText('POINT(".$latitude." ".$longitude.")',0)

            WHERE
                `events`.`id` = ".$event_id."

        ");

        $query->free_result();

    }

    public function get_event_info($id){

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
                IF(`users`.`id` = ".$user->id.", 'You', CONCAT(`users`.`first_name`, ' ',`users`.`last_name`)) AS `created by`,
                IF(`users`.`id` = ".$user->id.", true, false) AS `is_user`

            FROM
                `events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `events`.`id` = ".$id."

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
                ".$this->columns['time end']."

            FROM
                (SELECT `invites`.`event_id`, `invites`.`status` FROM `invites` WHERE `invites`.`user_id` = ".$user->id.") AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 2

            ORDER by
                `events`.`date_start` ASC,
                `events`.`time_start` ASC

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
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end']."

            FROM
                (SELECT `invites`.`event_id`, `invites`.`status` FROM `invites` WHERE `invites`.`user_id` = ".$user->id.") AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 'going' and
                DATE(`events`.`date_end`) <= STR_TO_DATE("."'Nov 6, 2014'".",'%M %d,%Y')

            ORDER by
                `events`.`date_start` ASC,
                `events`.`time_start` ASC

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
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end']."

            FROM
                (SELECT `invites`.`event_id`, `invites`.`status` FROM `invites` WHERE `invites`.`user_id` = ".$user->id.") AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 'invited'

            ORDER by
                `events`.`date_start` ASC,
                `events`.`time_start` ASC

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
        $query = $this->db->query("

            SELECT
                ".$this->columns['id'].",
                ".$this->columns['title'].",
                ".$this->columns['location'].",
                ".$this->columns['date start'].",
                ".$this->columns['time start'].",
                ".$this->columns['date end'].",
                ".$this->columns['time end']."

            FROM
                (SELECT `invites`.`event_id`, `invites`.`status` FROM `invites` WHERE `invites`.`user_id` = ".$user->id.") AS `invites`
                ,`events`

            JOIN
                `users`
            ON
                `events`.`user_id` = `users`.`id`

            WHERE
                `invites`.`event_id` = `events`.`id` and
                `invites`.`status` = 1 and
                DATE(`events`.`date_end`) >= STR_TO_DATE("."'Oct 24, 2014'".",'%M %d,%Y')

            ORDER by
                `events`.`date_start` ASC,
                `events`.`time_start` ASC

        ");

        $info = "";

        if ($query->num_rows() > 0)
        {
            $info = json_encode($query->result_array());
        }

        $query->free_result();

        return $info;

    }

    public function invite_friends_to_event($data){

        $friend_ids = $data['friend ids'];
        $event_id = $data['event id'];

        foreach($friend_ids as $friend_id){

            $query = $this->db->query("

                INSERT INTO

                    `plan8`.`invites` (

                        `id`,
                        `user_id`,
                        `event_id`,
                        `status`,
                        `geolocation`

                    )

                    VALUES (

                        NULL,
                        '".$friend_id."',
                        '".$event_id."',
                        '0',
                        GeomFromText(NULL)

                    )

            ");

        }

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


}

?>