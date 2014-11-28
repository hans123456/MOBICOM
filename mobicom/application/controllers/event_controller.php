<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Event_Controller extends CI_Controller {

	public function __construct(){

		parent::__construct();
		$this->load->library('ion_auth');
		$this->load->model('event_model');

	}

	public function upcoming_events(){

		$data['data'] = "";

		//if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_upcoming_events();

		//}

		$this->load->view('general_view', $data);

	}

	public function invited_events(){

		$data['data'] = "";

		//if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_invited_events();

		//}

		$this->load->view('general_view', $data);

	}

	public function finished_events(){

		$data['data'] = "";

		//if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_finished_events();

		//}

		$this->load->view('general_view', $data);

	}

	public function declined_events(){

		$data['data'] = "";

		//if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_declined_events();

		//}

		$this->load->view('general_view', $data);

	}

	public function event_attendees(){

		$data['data'] = "";
		$event_id = 1;//$this->input->post('event_id');

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_event_attendees($event_id);

		}

		$this->load->view('general_view', $data);

	}

	public function event_info(){

		$id = 1;//$this->input->post('id');
		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){

			$data['data'] = $this->event_model->get_event_info($id);

		}

		$this->load->view('general_view', $data);

	}

	public function edit_event_info(){

		$data['event id'] = $this->input->post('event_id');
		$data['location'] = $this->input->post('location');
		$data['title'] = $this->input->post('title');
		$data['description'] = $this->input->post('description');
		$data['date start'] = $this->input->post('date_starts');
		$data['time start'] = $this->input->post('time_start');
		$data['date end'] = $this->input->post('date_end');
		$data['time end'] = $this->input->post('time_end');
		$data['latitude'] = $this->input->post('latitude');
		$data['longitude'] = $this->input->post('longitude');

		if(true == $this->ion_auth->logged_in()){

			$this->event_model->edit_event_info($data);

		}

	}

	public function create_event(){

		$data['title'] = $this->input->post('title');
		$data['location'] = $this->input->post('location');
		$data['description'] = $this->input->post('description');
		$data['date start'] = $this->input->post('date starts');
		$data['time start'] = $this->input->post('time start');
		$data['date end'] = $this->input->post('date end');
		$data['time end'] = $this->input->post('time end');
		$data['latitude'] = $this->input->post('latitude');
		$data['longitude'] = $this->input->post('longitude');

		if(true == $this->ion_auth->logged_in()){
			$this->event_model->create_event($data);
		}

	}

	public function invite_friends_to_event(){

		$data['friend ids'] = explode(',', $this->input->post('friend ids'));
		$data['event id'] = $this->input->post('event id');

		if(true == $this->ion_auth->logged_in()){
			$this->event_model->invite_friends_to_event($data);
		}

	}

	public function attendee_location(){

		$attendee_id = $this->input->post('attendee id');
		$event_id = $this->input->post('event id');
		$data['data'] = "";

		if(true == $this->ion_auth->logged_in()){
			$data['data'] = $this->event_model->get_attendee_location($attendee_id, $event_id);
		}

		$this->load->view('general_view', $data);

	}

}

?>