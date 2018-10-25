import { Pipe, PipeTransform } from '@angular/core';
import { Contact } from '../model/contact';

@Pipe({
  name: 'fullName'
})
export class FullNamePipe implements PipeTransform {

  transform(contact: Contact): string {
    let result =contact.firstName+" "+contact.lastName;
    return result;
  }

}
