import { Usuario } from "./usuario";

export class Agenda {
    id: number;
    titulo: string;
    tipo: string;
    texto: string;
    dataHoraInicio: string;
    dataHoraFim: string;    
    tipoDestino: string;
    status: string;
    destino: number;
    usuario: Usuario;
}