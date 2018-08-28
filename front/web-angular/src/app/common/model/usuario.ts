import { Perfil } from './constants';

export class Usuario {
    id: number;
    nome: string;
    cpf: string;
    email: string;
    status: string;
    perfis: Perfil[];
    senha: string;
}
