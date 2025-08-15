package com.gdwho.api.infrastructure.controllers.game;

import java.util.List;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.infrastructure.controllers.game.dto.response.GetTotalGamesResponseDTO;

public class GameDTOMapper {

    public List<GetTotalGamesResponseDTO> toResponse(List<UserFilterDomain> domainGameList) {
        return domainGameList.stream()
                .map(element -> {
                    GetTotalGamesResponseDTO responseGameList = new GetTotalGamesResponseDTO(element.id(),
                            element.username());
                    return responseGameList;
                })
                .toList();
    }

}
