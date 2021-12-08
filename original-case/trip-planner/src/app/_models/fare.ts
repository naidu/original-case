export class Fare 
{
  constructor(
    public amount: number,
    public currency: string,
    public origin: string,
    public destination: string
  ) { }
}