import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { AdminEntity } from 'src/app/entities/admin.entity';

import { AdminService } from 'src/app/services/admin.service';


@Component({
  selector: 'admin-form',
  templateUrl: './admin.component.html'
})
export class AdminComponent {
  forms: any = [];
  adminDetails: Array<AdminEntity> = new Array();
  intId: number = 0;
  // adminId: number = 0;


  constructor(private adminService: AdminService) {

    this.adminService.getAllAdmins().subscribe((serverResponse: any) => {
      console.log('constrcutor serverResponse ', serverResponse);
      this.forms = serverResponse;
    })
  }

  adminForm = new FormGroup({
    adminName: new FormControl('r', [Validators.required]),
    adminPass: new FormControl('e', [Validators.required]),
    adminPhone: new FormControl(99, [Validators.required]),
    adminMail: new FormControl('r', [Validators.required])
  })




  submitAdminForm = () => {

    var admin_form = {
      adminName: this.adminForm.value['adminName'],
      adminPass: this.adminForm.value['adminPass'],
      adminPhone: this.adminForm.value['adminPhone'],
      adminMail: this.adminForm.value['adminMail'],

    };

    this.adminService.createNewAdmin(admin_form).subscribe((serverResponse: any) => {
      console.log('createNewAdmin - serviceResponse : ', serverResponse);

      this.forms.push(serverResponse);
    })

    // console.log(this.adminForm.value);
    // this.adminService.addAdmins(admin_form);

  }

  deleteAdminById = (id:any) => {

  }

}





