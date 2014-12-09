<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Event_Controller extends CI_Controller {

	public function __construct(){

		parent::__construct();
		$this->load->library('ion_auth');
		$this->load->model('event_model');

	}

	public function upcoming_events(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_upcoming_events();

		}

		$this->load->view('general_view', $data);

	}

	public function invited_events(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_invited_events();

		}

		$this->load->view('general_view', $data);

	}

	public function finished_events(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_finished_events();

		}

		$this->load->view('general_view', $data);

	}

	public function declined_events(){

		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_declined_events();

		}

		$this->load->view('general_view', $data);

	}

	public function event_attendees(){

		$data['data'] = "";
		$event_id = $this->input->post('event_id');

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_event_attendees($event_id);

		}

		$this->load->view('general_view', $data);

	}

	public function event_info(){

		$id = $this->input->post('event_id');
		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_event_info($id);

		}

		$this->load->view('general_view', $data);

	}

	public function edit_event_info(){

		$data['event_id'] = $this->input->post('event_id');
		$data['title'] = $this->input->post('title');
		$data['location'] = $this->input->post('location');
		$data['description'] = $this->input->post('description');
		$data['date_start'] = $this->input->post('date_start');
		$data['time_start'] = $this->input->post('time_start');
		$data['date_end'] = $this->input->post('date_end');
		$data['time_end'] = $this->input->post('time_end');
		$data['latitude'] = $this->input->post('latitude');
		$data['longitude'] = $this->input->post('longitude');

		$result['date_start'] = 'valid';
		$result['time_start'] = 'valid';
		$result['date_end'] = 'valid';
		$result['time_end'] = 'valid';
		$valid_edit_event = true;

		$date_start = strtotime($data['date_start']);
		$time_start = $data['time_start'];
		$date_end = strtotime($data['date_end']);
		$time_end = $data['time_end'];

		if($date_start > $date_end){

			$valid_edit_event = false;
			$result['date_start'] = 'invalid';

		}else if($date_start == $date_end && $time_start >= $time_end){

			$valid_edit_event = false;
			$result['time_start'] = 'invalid';
			$result['time_end'] = 'invalid';

		}

		if(true == $valid_edit_event){

			if(true == $this->ion_auth->logged_in()){

				$this->db->trans_start();
				$this->event_model->edit_event_info($data);
				$this->db->trans_complete();
				$data['data'] = 'success';

			}

		}else {

			$data['data'] = json_encode($result);

		}

		$this->load->view('register_view', $data);


	}

	public function create_event(){

		$data['title'] = $this->input->post('title');
		$data['location'] = $this->input->post('location');
		$data['description'] = $this->input->post('description');
		$data['date_start'] = $this->input->post('date_start');
		$data['time_start'] = '15:15';$this->input->post('time_start');
		$data['date_end'] = $this->input->post('date_end');
		$data['time_end'] = $this->input->post('time_end');
		$data['latitude'] = $this->input->post('latitude');
		$data['longitude'] = $this->input->post('longitude');

		$invite = array();
		$invite['friends_ids'] = $this->input->post('friends_ids');

		$result['date_start'] = 'valid';
		$result['time_start'] = 'valid';
		$result['date_end'] = 'valid';
		$result['time_end'] = 'valid';
		$valid_create_event = true;

		$date_start = strtotime($data['date_start']);
		$time_start = $data['time_start'];
		$date_end = strtotime($data['date_end']);
		$time_end = $data['time_end'];

		if(false == $this->ion_auth->logged_in()){
			return;
		}

		if($date_start > $date_end){

			$valid_create_event = false;
			$result['date_start'] = 'invalid';

		}else if($date_start == $date_end && $time_start >= $time_end){

			$valid_create_event = false;
			$result['time_start'] = 'invalid';
			$result['time_end'] = 'invalid';

		}

		if(true == $valid_create_event){

			 if(true == $this->ion_auth->logged_in()){
				$this->db->trans_start();
				$this->event_model->create_event($data);
				$invite['event_id'] = $this->db->insert_id();
				$this->event_model->invite_self($invite);
				$this->event_model->invite_friends_to_event($invite);
				$this->db->trans_complete();
				$data['data'] = 'success';
			 }

		}else {

			$data['data'] = json_encode($result);

		}

		$this->load->view('register_view', $data);

	}

	public function invite_friends_to_event(){

		$data['friends_ids'] = $this->input->post('friends_ids');
		$data['event_id'] = $this->input->post('event id');

		if(true == $this->ion_auth->logged_in()){
			$this->event_model->invite_friends_to_event($data);
		}

	}

	public function friends_not_yet_invited(){

		$event_id = $this->input->post('event_id');

		if(true == $this->ion_auth->logged_in()){
			$data['data'] = $this->event_model->get_friends_not_yet_invited($event_id);
		}

		$this->load->view('general_view', $data);

	}

	public function attendee_location(){

		$attendee_id = $this->input->post('attendee_id');
		$event_id = $this->input->post('event_id');
		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){
			$data['data'] = $this->event_model->get_attendee_location($attendee_id, $event_id);
		}

		$this->load->view('general_view', $data);

	}

	public function invitation_status(){

		$event_id = $this->input->post('event_id');
		$invitation_status = $this->input->post('invitation_status');

		if(true == $this->ion_auth->logged_in()){
			$data['data'] = $this->event_model->set_invitation_status($event_id, $invitation_status);
		}

	}

}

?>