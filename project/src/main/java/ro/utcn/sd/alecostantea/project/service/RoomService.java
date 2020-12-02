package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.Room;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RoomRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {  //hihi
    private final RepositoryFactory factory;

    @Transactional
    public List<Room> findAll() {
        RoomRepository repo = factory.createRoomRepository();
        return repo.findALL();
    }

    @Transactional
    public Room save(Room room){
        return factory.createRoomRepository().save(room);
    }

    @Transactional
    public void remove(Room room){
        factory.createRoomRepository().remove(room);
    }

    @Transactional
    public Room findById(int id)
    {
        return factory.createRoomRepository().findByID(id).get();
    }

}
