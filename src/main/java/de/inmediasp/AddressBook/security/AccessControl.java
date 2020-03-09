package de.inmediasp.AddressBook.security;

import de.inmediasp.AddressBook.Service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessControl {

    private static final Logger log = LoggerFactory.getLogger(AccessControl.class);

    Map<String, List<String>> userGroupsMap = new HashMap<String, List<String>>();

    private final AddressBookService addressBookService;

    @Autowired
    public AccessControl(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    public boolean checkGroupByUser(String ResourceOwnerGroup) {
        String userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<String> userGroups = userGroupsMap.get(userName);
        String userGroup = userGroups.get(0);
        if(ResourceOwnerGroup.equals(userGroup)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkGroupById(Long id) {
        String userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<String> userGroups = userGroupsMap.get(userName);
        String userGroup = userGroups.get(0);
        String ResourceOwnerGroup = addressBookService.findById(id).getOwner();
        if(ResourceOwnerGroup.equals(userGroup)){
            return true;
        }else{
            return false;
        }
    }

    public Map<String, List<String>> getUserGroupsMap() {
        return userGroupsMap;
    }

    public void setUserGroupsMap(Map<String, List<String>> userGroupsMap) {
        this.userGroupsMap = userGroupsMap;
    }
}
