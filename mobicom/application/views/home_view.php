<!DOCTYPE HTML>
<html>

	<head>

		<style type="text/css">

		label, input {
			display: block;
		}

		label {
			margin-bottom: 5px;
		}

		form {
			margin-top: 20px;
		}

		</style>

	</head>

	<body>

		<form action="login" method="post">

			<label>Username : <input type="username" name='username'><br /></label>
			<label>Password : <input type="password" name='password'><br /></label>
			<label><input type="submit" value="Submit"></label>

		</form>

		<form action="register" method="post">

			<label>First Name : <input type="test" name='first_name'><br /></label>
			<label>Last Name : <input type="test" name='last_name'><br /></label>
			<label>Username : <input type="username" name='username'><br /></label>
			<label>Password : <input type="password" name='password'><br /></label>
			<label>Confirm Password : <input type="password" name='confirm_password'><br /></label>
			<label>E-mail : <input type="email" name='email'><br /></label>
			<input type="submit" value="Submit">

		</form>

	</body>

</html>
