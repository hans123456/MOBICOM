<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	http://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There area two reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router what URI segments to use if those provided
| in the URL cannot be matched to a valid route.
|
*/

$route['default_controller'] = 'controller/home';
$route['home'] = 'controller/home';

$route['login'] = 'user_controller/login';
$route['logout'] = 'user_controller/logout';
$route['register'] = 'user_controller/register';
$route['change_profile_picture'] = 'user_controller/change_profile_picture';
$route['search_profile_by_username'] = 'user_controller/search_profile_by_username';

$route['(:any)_events'] = 'event_controller/$1_events';
$route['event_info'] = 'event_controller/event_info';
$route['edit_event_info'] = 'event_controller/edit_event_info';
$route['create_event'] = 'event_controller/create_event';
$route['delete_event'] = 'event_controller/delete_event';
$route['invite_friends'] = 'event_controller/invite_friends_to_event';
$route['event_attendees'] = 'event_controller/event_attendees';
$route['attendee_location'] = 'event_controller/attendee_location';
$route['invite_friends_to_event'] = 'event_controller/invite_friends_to_event';
$route['friends_not_yet_invited'] = 'event_controller/friends_not_yet_invited';
$route['send_location'] = 'event_controller/send_location';
$route['invitation_status'] = 'event_controller/invitation_status';

$route['friends'] = 'friend_controller/friends';
$route['friend_requests'] = 'friend_controller/friend_requests';
$route['sent_friend_requests'] = 'friend_controller/sent_friend_requests';
$route['send_friend_request'] = 'friend_controller/send_friend_request';
$route['accept_friend_request'] = 'friend_controller/accept_friend_request';
$route['delete_friend'] = 'friend_controller/delete_friend';

$route['404_override'] = '';

/* End of file routes.php */
/* Location: ./application/config/routes.php */
