package org.mvoks.datatransfer.service;

import org.jvnet.hk2.annotations.Contract;
import org.mvoks.datatransfer.dto.auth.JwtRefresh;
import org.mvoks.datatransfer.dto.auth.JwtRequest;
import org.mvoks.datatransfer.dto.auth.JwtResponse;

@Contract
public interface AuthService {

    JwtResponse login(JwtRequest jwtRequest);

    JwtResponse refresh(JwtRefresh jwtRefresh);
}