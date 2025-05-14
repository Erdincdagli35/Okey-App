package com.edsoft.okey_app.service;

import com.edsoft.okey_app.response.BestPlayerInfo;
import com.edsoft.okey_app.response.GamePrepareInfo;
import com.edsoft.okey_app.response.GameStartInfo;

public interface OkeyService {
    GamePrepareInfo getGamePrepareInfo();

    GameStartInfo gameStart();

    Integer getBestPlayer();
}
