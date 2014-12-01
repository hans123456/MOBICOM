<?php

    $this->load->library('ion_auth');

    if(true == $this->ion_auth->logged_in())
    {

        echo $data;

    }
    else
    {
        echo "fail";
    }

?>

