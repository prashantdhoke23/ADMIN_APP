import { Injectable } from '@angular/core';
import { AdminEntity } from '../entities/admin.entity';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class AdminService {

    getUrl = 'http://localhost:7779/admin/';
    deleteUrl = 'http://localhost:7779/admin/admin';
    postUrl = 'http://localhost:7779/admin/admin/user';

    token: string = '';
    // test: string = 'Bearer ';
    constructor(private httpClient: HttpClient) {

    }

    getAllAdmins(): Observable<any> {
        let httpOptions = {
            // headers: new HttpHeaders({
            //     'Access-Control-Allow-Origin': '*',
            // }),
        };
        return this.httpClient.get<any>(this.getUrl, httpOptions);
    }

    addAdmins(adminObject: AdminEntity) {
        this.admin.push(adminObject);
    }

        createNewAdmin(adminForms: any): Observable<any> {
        
        let httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'


            }),
        };
    

        return this.httpClient.post<any>(
            this.postUrl,
            JSON.stringify(adminForms),
            httpOptions
        );
    }


    admin = [
        {
            adminName: 'raja',
            adminPass: 'pass',
            adminPhone: 1234512345,
            adminMail:'raja@gmail.com'
        },
    ];





}
