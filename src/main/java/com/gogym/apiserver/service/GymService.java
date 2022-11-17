package com.gogym.apiserver.service;

import com.gogym.apiserver.dto.gym.Gym;
import com.gogym.apiserver.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GymService {
    @Autowired
    private GymRepository gymRepository;

//    public GymCreateResDTO addGym(GymCreateReqDTO dto) {
//        Gym gym = Gym.builder()
//                .id(dto.getId())
//                .name(dto.getName())
//                .tel(dto.getTel())
//                .address(dto.getAddress())
//                .build();
//
//        Gym saved = gymRepository.save(gym);
//
//        // TODO: model mapper
//        GymCreateResDTO res = GymCreateResDTO.builder()
//                .id(gym.getId())
//                .name(gym.getName())
//                .tel(gym.getTel())
//                .address(gym.getAddress())
//                .build();
//
//        return res;
//    }
//

    public Gym addGym(Gym req) {
        return gymRepository.save(req);
    }

    public Gym getGymById(String id) {
        Optional<Gym> byId = gymRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        return byId.get();
    }

    public List<Gym> getGymByAll() {
        return gymRepository.findAll();
    }

    public Gym updateGym(Gym req) {
        return gymRepository.save(req);
    }

    public int deleteGym(String id) {
        Optional<Gym> byId = gymRepository.findById(id);
        if (byId.isEmpty()) {
            return 0;
        }
        gymRepository.delete(byId.get());
        return 1;
    }

}
