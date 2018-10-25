import { Component, OnInit } from '@angular/core';
import { Contact } from '../../model/contact';
import { ActivatedRoute, Router } from '@angular/router';
import { ContactService } from '../../service/contact.service';

@Component({
  selector: 'app-delete-contact',
  templateUrl: './delete-contact.component.html',
  styleUrls: ['./delete-contact.component.css']
})
export class DeleteContactComponent implements OnInit {

  contact: Contact;
  ladyLogo: string;
  gentLogo: string;
  constructor(private contactService: ContactService,
    private activatedRoute: ActivatedRoute,
    private router : Router
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

  doDelete(isConfirmed:boolean){
    if(isConfirmed){
      this.contactService.deleteContactById(this.contact.contactId).subscribe(
        (resp) =>{
          if(resp.ok){
            this.router.navigateByUrl("/?opt=d&id="+this.contact.contactId);
          }
        }
      );
    }else{
      this.router.navigateByUrl("/");
    }
  }
}
