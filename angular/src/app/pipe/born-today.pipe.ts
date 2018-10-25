import { Pipe, PipeTransform } from '@angular/core';
import { Contact } from '../model/contact';

@Pipe({
  name: 'bornToday'
})
export class BornTodayPipe implements PipeTransform {

  transform(contacts: Contact[]):Contact[] {
    let today=new Date();
    return contacts.filter(c => {
      let dob = new Date(c.dateOfBirth);
      return dob.getDate()==today.getDate() &&
            dob.getMonth()==today.getMonth();
    });
  }

}
