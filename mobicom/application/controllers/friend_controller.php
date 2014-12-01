<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Friend_Controller extends CI_Controller {

	public function __construct(){

		parent::__construct();
		$this->load->library('ion_auth');
		$this->load->model('friend_model');

	}

	public function friends(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->friend_model->get_friends();

		}

		$this->load->view('general_view', $data);

	}

	public function friend_requests(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->friend_model->get_friend_requests();

		}

		$this->load->view('general_view', $data);

	}

	public function sent_friend_requests(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->friend_model->get_sent_friend_requests();

		}

		$this->load->view('general_view', $data);

	}

	public function accept_friend_request(){

		$friend_id = $this->input->post('friend_id');

		if(true == $this->ion_auth->logged_in()){

			$this->friend_model->accept_friend_request($friend_id);

		}

	}

	public function delete_friend(){

		$friend_id = $this->input->post('friend_id');

		if(true == $this->ion_auth->logged_in()){

			$this->friend_model->delete_friend($friend_id);

		}

	}

	public function send_friend_request(){

		$friend_id = $this->input->post('friend_id');

		if(true == $this->ion_auth->logged_in()){

			$this->friend_model->send_friend_request($friend_id);

		}

	}

}

?>