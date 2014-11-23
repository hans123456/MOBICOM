<?php

	if(true == $this->ion_auth->logged_in())
	{
		if('' == $data)
		{
			echo 'empty';
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