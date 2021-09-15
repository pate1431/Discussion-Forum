package ca.sheridancollege.pate1431.securities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.pate1431.beans.Role;
import ca.sheridancollege.pate1431.repositories.MembersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MembersRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ca.sheridancollege.pate1431.beans.Members user = memberRepository.findByUsername(username);

		if (user == null) {
			System.out.println("User not found: " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in database.");
		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoleList()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPass(), grantList);
		return userDetails;
	}

}