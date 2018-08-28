import { Perfil } from './constants';

export class Usuario {
    id: number;
    nome: string;
    nomeCompleto: string;
    cpf: string;
    email: string;
    status: string;
    perfis: Perfil[];
    senha: string;
    avatar?: string;

}
