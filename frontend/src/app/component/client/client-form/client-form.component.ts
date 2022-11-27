import { Component, Input, OnInit } from '@angular/core';
import { CountryService } from "../../../service/country/country.service";
import { ClientService } from "../../../service/client/client.service";
import { ClientAction } from "../../../model/client-action";
import ClientComponentBase from "../base/ClientComponentBase";
import Country from "../../../model/country";
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent extends ClientComponentBase implements OnInit {
  @Input() action: ClientAction | null = null;
  @Input() id: number | null = null;
  public countries: Country[] = [];
  public invalid: boolean;

  constructor(private countryService: CountryService,
              private clientService: ClientService,
              private router: Router) {
    super();
    this.invalid = false;
    this.countryService.getCountries()
      .subscribe((countries: Country[]) => {
        this.countries = countries
      });
  }

  ngOnInit(): void {
    this.load();
  }

  load() {
    if (this.isEdit() && this.isIdNotNull()) {
      this.clientService.getClient(this.id!).subscribe((client) => {
        this.client = client;
      }, (e: HttpErrorResponse) => {
        if (e.status == 404) this.router.navigateByUrl("/");
      });
    }
  }

  submit() {
    if (this.client.countryId == 0) {
      this.invalid = true;
      return;
    }
    if (this.isAdd()) {
      this.clientService.add(this.client).subscribe((response) => {
        this.router.navigateByUrl('/');
      }, (e: HttpErrorResponse) => {
        if (e.status == 400) this.invalid = true;
      })
    } else if (this.isEdit() && this.isIdNotNull()) {
      this.clientService.edit(this.id!, this.client).subscribe((response) => {
        this.router.navigateByUrl('/');
      }, (e: HttpErrorResponse) => {
        if (e.status == 400) this.invalid = true;
      });
    }
  }

  private isEdit = () => this.action == ClientAction.EDIT;
  private isAdd = () => this.action == ClientAction.ADD;
  private isIdNotNull = () => this.id !== null;
}
