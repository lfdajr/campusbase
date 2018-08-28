import { Usuario } from './usuario';
import { Aula } from './aula';

export class Curso {
    id: number;
    codigo: string;
    titulo: string;
    nomeDisciplina: string;
    descricao: string;
    dataHoraCriacao: string;
    professor: Usuario;
    status?: string;
    ordenacao?: number;
    aulas?: Aula[];

    public assign(init?:Partial<Curso>) {
        Object.assign(this, init);
    }   
}