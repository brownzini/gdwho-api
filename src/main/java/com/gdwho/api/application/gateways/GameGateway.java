package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.github.fge.jsonpatch.JsonPatch;

public interface GameGateway {
    void   createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId);
    double guessResult(String input, Long userId);
    void   responseUpdate(Long userId, RoleEnum role, String dataResponse);
    void   dataUpdate(Long dataId, Long userId, RoleEnum role, String value);
    void   entrieUpdate(Long entryId, Long userId, RoleEnum role, JsonPatch patch);
    void   deleteData(Long dataId, Long userId, RoleEnum role);
    void   deleteEntrie(Long entrieId, Long userId, RoleEnum role);
}
