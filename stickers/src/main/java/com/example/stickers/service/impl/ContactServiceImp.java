package com.example.stickers.service.impl;

import com.example.stickers.constants.ApplicationConstants;
import com.example.stickers.dto.ContactRequestDto;
import com.example.stickers.dto.ContactResponseDto;
import com.example.stickers.entity.Contact;
import com.example.stickers.entity.Order;
import com.example.stickers.exception.ResourceNotFoundException;
import com.example.stickers.repository.ContactRepository;
import com.example.stickers.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements IContactService {

    private final ContactRepository contactRepository;
    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {

            Contact contact= transformToEntity(contactRequestDto);
            contactRepository.save(contact);
            return true;

    }

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contacts = contactRepository.findByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contacts.stream().map(this::mapToContactResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact", "ContactId", contactId.toString()));
        contact.setStatus(status);
        contactRepository.save(contact);
    }

    private ContactResponseDto mapToContactResponseDTO(Contact contact) {
        ContactResponseDto responseDTO = new ContactResponseDto(
                contact.getContactId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
        return responseDTO;
    }


    private Contact transformToEntity(ContactRequestDto contactRequestDto){
        Contact contact= new Contact();
        BeanUtils.copyProperties(contactRequestDto,contact);
        contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact;
    }

}
