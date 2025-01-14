package com.mobicom.moihana.Model;

public class HTML {

	// general
	public static final String website = "http://192.168.1.149/mobicom/index.php/";
	public static final String user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.65 Safari/537.36";
	public static final String element_id = "result";
	public static final String session_id = "ci_session";
	public static String SessionID = null;
	public static User userProfile = null;
	
	// user
	public static final String post_username = "username";
	public static final String post_email_address = "email";
	public static final String post_password = "password";
	public static final String post_confirm_password = "confirm_password";
	public static final String post_first_name = "first_name";
	public static final String post_last_name = "last_name";
	
	// user and event
	public static final String post_latitude = "latitude";
	public static final String post_longitude = "longitude";

	// friend
	public static final String post_friend_id = "friend_id";
	public static final String post_invited_friends = "friends_ids";
	
	// event
	public static final String post_event_id = "event_id";
	public static final String post_title = "title";
	public static final String post_date_start = "date_start";
	public static final String post_time_start = "time_start";
	public static final String post_date_end = "date end";
	public static final String post_time_end = "time_end";
	public static final String post_location = "location";
	public static final String post_description = "description";
	public static final String post_invitation_status = "invitation_status";
	
	// links
	public static final String login = "login";
	public static final String logout = "logout";
	public static final String register = "register";
	public static final String change_profile_picture = "change_profile_picture";
	public static final String edit_profile_info = "edit_profile_info";
	
	public static final String friends = "friends";
	public static final String friend_requests = "friend_requests";
	public static final String sent_friend_requests = "sent_friend_requests";
	public static final String send_friend_request = "send_friend_request";
	
	public static final String search_profile_by_username = "search_profile_by_username";
	public static final String friend_info = "friend_info";
	public static final String accept_friend_request = "accept_friend_request";
	public static final String delete_friend = "delete_friend";
	
	public static final String create_event = "create_event";
	public static final String delete_event = "delete_event";
	public static final String edit_event_info = "edit_event_info";
	public static final String event_info = "event_info";
	public static final String event_attendees = "event_attendees";
	public static final String attendee_location = "attendee_location";
	public static final String invite_friends = "invite_friends";
	public static final String friends_not_yet_invited = "friends_not_yet_invited";
	public static final String send_location = "send_location";
	
	public static final String declined_events = "declined_events";
	public static final String finished_events = "finished_events";
	public static final String invited_events = "invited_events";
	public static final String upcoming_events = "upcoming_events";
	public static final String invitation_status = "invitation_status";
	
}
