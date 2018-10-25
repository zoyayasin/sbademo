import { Component, OnInit } from '@angular/core';
import { Contact } from '../../model/contact';
import { ContactService } from '../../service/contact.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  title: string;
  logoUrl: string;
  ladyLogo: string;
  gentLogo: string;
  birthLogo:string;
  contacts: Contact[];
  msg : string;
  constructor(private contactService:ContactService,
    private activatedRoute:ActivatedRoute) {
    this.title = "Address Book 3.0";
    this.logoUrl = "/assets/images/adbLogo.png"; 
    this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/gent.png";
    this.birthLogo = "/assets/images/birthday.png";
  }

  ngOnInit() {
    this.contactService.getAllContacts().subscribe(
      (data) => this.contacts = data
    );
    this.activatedRoute.queryParams.subscribe(
      (params)=>{
        let contactId=params['id'];
        let operation=params['opt'];
        if(contactId && operation){
          this.msg = "Contact # "+contactId + " is successfully "+
          (operation=='d'?'Deleted':(operation=='a'?'added':'updated'));
          
        }else{
          this.msg=undefined;
        }
      }
    );
  }
}
