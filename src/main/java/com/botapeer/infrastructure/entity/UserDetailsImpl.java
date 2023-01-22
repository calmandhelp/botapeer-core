package com.botapeer.infrastructure.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	private final User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public int getId() {
		return this.user.getId();
	}

	@Override
	public String getPassword() {
		Password password = this.user.getPassword();
		String strPassword = password.getPassword();
		return strPassword;
	}

	@Override
	public String getUsername() {
		UserName userName = this.user.getName();
		String strUserName = userName.getName();
		return strUserName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
