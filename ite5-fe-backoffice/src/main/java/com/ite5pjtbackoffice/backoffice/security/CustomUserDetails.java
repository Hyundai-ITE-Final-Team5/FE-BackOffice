package com.ite5pjtbackoffice.backoffice.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	public CustomUserDetails(
			String mid, 
			String mpassword, 
			List<GrantedAuthority> mauthorities) {
		super(mid, mpassword, true, true, true, true, mauthorities);
	}
	

}

