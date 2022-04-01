package ru.itmo;

import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;
import ru.itmo.service.CatService;
import ru.itmo.service.FriendshipService;
import ru.itmo.service.OwnerService;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.time.LocalDate;

public class Program {
    public static void main(String[] args) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        CatService catService = new CatService();
        OwnerService ownerService = new OwnerService();
        //Owner owner = ownerService.create("Tanya", LocalDate.of(2002, 5, 29));
        //catService.create("Klaus", LocalDate.of(2015, 2, 5), Color.GRAY, "Cymric", owner);
        FriendshipService friendshipService = new FriendshipService();
        friendshipService.create(23, 24);
    }

}
