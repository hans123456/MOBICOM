<?php

	if(true == $this->ion_auth->logged_in())
	{
		if('' == $data)
		{
			echo 'no event with that id';
		}
		else
		{
			echo $data;
		}
	}
	else
	{
		echo 'fail';
	}

?>