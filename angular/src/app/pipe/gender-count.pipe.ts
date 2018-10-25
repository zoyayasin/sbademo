import { Pipe, PipeTransform } from '@angular/core';
import { Contact } from '../model/contact';

@Pipe({
  name: 'genderCount'
})
export class GenderCountPipe implements PipeTransform {

  transform(contacts:Contact[],gender:string): number {
    return contacts.filter(c=>c.gender==gender).length;
  }

}
