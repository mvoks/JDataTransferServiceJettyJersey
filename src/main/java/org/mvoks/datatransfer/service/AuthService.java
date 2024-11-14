package org.mvoks.datatransfer.service;

import org.jvnet.hk2.annotations.Contract;
import org.mvoks.datatransfer.dto.JwtRefresh;
import org.mvoks.datatransfer.dto.JwtRequest;
import org.mvoks.datatransfer.dto.JwtResponse;

@Contract
public interface AuthService {

    JwtResponse login(JwtRequest jwtRequest);

    JwtResponse refresh(JwtRefresh jwtRefresh);
}