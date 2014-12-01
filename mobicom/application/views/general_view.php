<html>

	<body>

		<div id='result'>

			<?php

				$this->load->library('ion_auth');

				// if(true == $this->ion_auth->logged_in())
				// {
					if('' == $data)
					{
						echo 'empty';
					}
					else
					{
						echo $data;
					}
				// }
				// else
				// {
				// 	echo "fail";
				// }

			?>

		</div>

	</body>

</html>