import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import ClientListItem from "../../model/client-list-item";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import Client from "../../model/client";
import routes from "../../util/routes";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private endpoint = `${environment.api}/${routes.client}`;

  constructor(private http: HttpClient) { }

  getClients(): Observable<ClientListItem[]> {
    return this.http.get<ClientListItem[]>(this.endpoint)
  }

  add(client: Client): Observable<Client> {
    return this.http.post<Client>(`${this.endpoint}`, client);
  }

  getClient(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.endpoint}/${id}`)
  }

  edit(id: number, client: Client): Observable<Client> {
    return this.http.post<Client>(`${this.endpoint}/${id}`, client)
  }
}
