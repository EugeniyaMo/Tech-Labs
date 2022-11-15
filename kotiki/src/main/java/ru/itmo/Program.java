package ru.itmo;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

public class Program {
    public static void main(String[] args) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        //CatService catService = new CatService();
        //IOwnerService ownerService = new IOwnerService();
        //Owner owner = ownerService.create("Tanya", LocalDate.of(2002, 5, 29));
        //catService.create("Klaus", LocalDate.of(2015, 2, 5), Color.GRAY, "Cymric", owner);
        //FriendshipService friendshipService = new FriendshipService();
        //friendshipService.create(23, 24);
    }

}
