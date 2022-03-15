package com.mab.merchantapi.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mab.merchantapi.util.AesUtil;

public class MerchantPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		AesUtil util = new AesUtil();
		return util.encrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		AesUtil util = new AesUtil();
		String str = rawPassword.toString();
		String base64 = new String(util.base64Decode(str));
		String password = util.encrypt(base64) ;
		
		if (password.equals(encodedPassword))
			return true;
		else
			return false;
	}

}
