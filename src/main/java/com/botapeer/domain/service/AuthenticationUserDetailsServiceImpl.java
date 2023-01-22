package com.botapeer.domain.service;

//public class AuthenticationUserDetailsServiceImpl
//		implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
//
//	@Override
//	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
//
//		String credential = token.getCredentials().toString();
//
//		System.out.println(credential);
//		if (credential.isEmpty()) {
//			throw new UsernameNotFoundException("Authorization header must not be empty.");
//		}
//
//		switch (credential) {
//		case "key1":
//			// GetItemという名前の権限をもつユーザを作成
//			return new UserEntity("user", "", AuthorityUtils.createAuthorityList("GetItem"));
//		case "key2":
//			// // GetItem, GetEmployeeという名前の権限をもつユーザを作成
//			return new UserEntity("manager", "", AuthorityUtils.createAuthorityList("GetItem", "GetEmployee"));
//		default:
//			throw new UsernameNotFoundException("Invalid authorization header");
//		}
//	}
//
//}
