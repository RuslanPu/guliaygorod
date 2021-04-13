package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.EntryServiceImpl;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.MetaFileDbService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping()
public class CrudController {
    @Autowired
    private UserService service;

    @Autowired
    private EntryServiceImpl entryService;

    @Autowired
    private MetaFileDbService offerService;

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/admin/add")
    public ResponseEntity<JsonObject> getRoles() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.setAllRoles(service.getAllRole());

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/user/requestById")
    public ResponseEntity<JsonObject> getUserById(@RequestBody User user) {
        Long id = user.getId();
        User userById = service.getUserById(id);
        List<Role> allRoles = service.getAllRole();
        JsonObject jsonObject = new JsonObject();
        jsonObject.setUser(userById);
        jsonObject.setAllRoles(allRoles);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/entry/requestById")
    public ResponseEntity<JsonObject> getEntryById(@RequestBody EntryMainPage entry) {
        Long id = entry.getId();
        EntryMainPage entryById = entryService.getEntryById(id);

        JsonObject jsonObject = new JsonObject();
        jsonObject.setEntry(entryById);
        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/entry/updateEntry")
    public String updateUser(@RequestBody EntryMainPage entry) {
        entryService.edit(entry);
        return "update";
    }

    @PostMapping("/offer/requestById")
    public ResponseEntity<JsonObject> getOfferById(@RequestBody MetaFileDB offer) {
        Long id = offer.getId();
        MetaFileDB offerById = offerService.getFileById(id);

        JsonObject jsonObject = new JsonObject();
        jsonObject.setOffer(offerById);
        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }
    @PostMapping("/offer/add")
    public ResponseEntity addOffer(@ModelAttribute UploadForm form) throws Exception{

        try{
            storageService.store(form.getFile()[0]);
            String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
            byte[] data1 = form.getFile()[0].getBytes();

            String idImage = offerService.getFileIdByParam(fileName1,data1);
            MetaFileDB metaFileDB = new MetaFileDB();

            metaFileDB.setNameOffer(form.getName());
            metaFileDB.setOfferCategory(form.getCategory());
            metaFileDB.setDescOffer(form.getDescOffer());
            metaFileDB.setPriceOffer(form.getCost());
            metaFileDB.setImageId(idImage);
            offerService.add(metaFileDB);
            System.out.println("file add");
            metaFileDB.setId(metaFileDB.getId());



            return new ResponseEntity(metaFileDB, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/offer/update")
    public ResponseEntity updateOffer(@ModelAttribute UploadForm form) throws Exception{
            String imageId;
            //if old image
        try{
            if (form.getFile()[0].getOriginalFilename() == "") {
                imageId = offerService.getFileById(form.getId()).getImageId();
            } else {
                String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
                byte[] data1 = form.getFile()[0].getBytes();

                imageId = offerService.getFileIdByParam(fileName1,data1);
            }


            System.out.println("find image");
            MetaFileDB metaFileDB = offerService.getFileById(form.getId());
            System.out.println(metaFileDB);
            metaFileDB.setNameOffer(form.getName());
            metaFileDB.setOfferCategory(form.getCategory());
            metaFileDB.setDescOffer(form.getDescOffer());
            metaFileDB.setPriceOffer(form.getCost());
            metaFileDB.setImageId(imageId);
            offerService.edit(metaFileDB);
            System.out.println("file edit");


            return new ResponseEntity(metaFileDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            //if new image
            try {
                if (form.getFile()[0].getOriginalFilename() == null) {
                    imageId = offerService.getFileById(form.getId()).getImageId();
                } else {
                    storageService.store(form.getFile()[0]);
                    String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
                    byte[] data1 = form.getFile()[0].getBytes();
                    imageId = offerService.getFileIdByParam(fileName1,data1);

                }



                MetaFileDB metaFileDB = offerService.getFileById(form.getId());
                System.out.println(metaFileDB);
                metaFileDB.setNameOffer(form.getName());
                metaFileDB.setOfferCategory(form.getCategory());
                metaFileDB.setDescOffer(form.getDescOffer());
                metaFileDB.setPriceOffer(form.getCost());
                metaFileDB.setImageId(imageId);
                offerService.edit(metaFileDB);
                System.out.println("file edit");
                return new ResponseEntity(metaFileDB,HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }

    }

    @PostMapping("/offer/delete")
    public String deleteOffer(@RequestBody MetaFileDB file) {
        offerService.delete(file);
        return  "delete";
    }



    @PostMapping("/admin/delete")
    public String deleteUser(@RequestBody User user) {
        Long id = user.getId();
        System.out.println(id);
        User userDeleted = service.getUserById(id);
        service.delete(userDeleted);
        return  "delete";
    }

    @PostMapping("/admin/add")
    public String addUser(@RequestBody User user) {
        String[] roles = new String[user.getRoles().size()];
        for (int i = 0 ; i < user.getRoles().size(); i++) {
            roles[i] = user.getRoles().get(i).getName();
        }
        service.add(user, roles);
        return  "add";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println(user.getRoles());
        String[] roles = new String[user.getRoles().size()];
        for (int i = 0 ; i < user.getRoles().size(); i++) {
            roles[i] = user.getRoles().get(i).getName();
        }
        service.edit(user, roles);
        return "update";
    }

    @PostMapping("/user/checkEmail")
    public ResponseEntity<JsonObject> checkEmail(@RequestBody User user) {
//        Long id = user.getId();
        String email = user.getEmail();
//        User userById = service.getUserById(id);
        boolean unicEmail;
        if (!service.unicEmail(email)) {
            unicEmail = false;
        } else { unicEmail = true; }

        JsonObject jsonObject = new JsonObject();
        jsonObject.setUnicEmail(unicEmail);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }



}

