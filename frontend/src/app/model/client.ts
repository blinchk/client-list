export default interface Client {
  firstName: string;
  lastName: string;
  username: string;
  email?: string;
  address: string;
  countryId: number | null;
}
