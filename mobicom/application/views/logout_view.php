<?php


    $this->load->library('ion_auth');

    if($this->ion_auth->logged_in()==false)
        echo "successfuly logged out";

?>