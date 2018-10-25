import { Component, OnInit } from '@angular/core';
import { Contact } from '../../model/contact';
import { ContactService } from '../../service/contact.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  contacts:Contact[];
  
  ladyLogo:string;
  gentLogo:string;

  constructor(
    private contactService:ContactService
, private activatedRoute : ActivatedRoute  ) {
    this.ladyLogo="/assets/images/lady.png";
    this.gentLogo="/assets/images/gent.png";
   }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) =>{
        let field = params['field'];
        let srchValue = params['srchValue'];

        console.log(field +":"+srchValue);

        if(field && srchValue){
          this.contactService.searchContacts(field,srchValue).subscribe(
            (data) => this.contacts=data,
            (err)=>this.contacts=undefined
          );
        }else{
          this.contactService.getAllContacts().subscribe(
            (data) => this.contacts=data,
            (err)=>this.contacts=undefined
          );
        }
      }
    );
    
  }

}
