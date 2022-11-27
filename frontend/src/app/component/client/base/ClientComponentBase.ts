import { ClientAction } from "src/app/model/client-action";
import Client from "../../../model/client";
import { Inject } from "@angular/core";
import { CountryService } from "../../../service/country/country.service";

export default class ClientComponentBase {
  client: Client = {
    firstName: '',
    lastName: '',
    username: '',
    address: '',
    countryId: null,
  };

  public get ClientAction() {
    return ClientAction;
  }
}
