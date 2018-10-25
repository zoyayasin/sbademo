import { Component, OnInit } from '@angular/core';
import { Contact } from '../../model/contact';
import { ContactService } from '../../service/contact.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.css']
})
export class ContactDetailsComponent implements OnInit {

  contact: Contact;
  ladyLogo: string;
  gentLogo: string;
  constructor(private contactService: ContactService,
    private activatedRoute: ActivatedRoute
  ) {
  this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/gent.png";
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let contactId = params['id'];
        if (contactId) {
          this.contactService.getContactById(contactId).subscribe(
            (data) => this.contact = data
          );
        }
      }
    );
  }

}
