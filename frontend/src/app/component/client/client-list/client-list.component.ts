import { Component, OnInit } from '@angular/core';
import ClientListItem from "../../../model/client-list-item";
import { ClientService } from "../../../service/client/client.service";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  public clients: ClientListItem[] = []

  constructor(private clientService: ClientService) {
    this.clientService.getClients()
      .subscribe(clients => {
        this.clients = clients;
      });
  }

  ngOnInit(): void {
  }

}
