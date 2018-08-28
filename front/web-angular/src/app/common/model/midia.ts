import { Usuario } from './usuario';

export class Midia {
    id: number;
    descricao: string;
    caminho: string;
    tipo: string;
    tipoDestino: string;
    destino: number;
    dataHora: string;
    remetente: Usuario;
    status: string;
}