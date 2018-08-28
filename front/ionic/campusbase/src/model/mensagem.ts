
import { Usuario } from './usuario';

export class Mensagem {
    id: number;
    mensagem: string;
    arquivos: string;
    dataHora: string;
    tipo: TipoMensagem;
    destino: number;
    remetente: Usuario;
}

enum TipoMensagem
{
    // NAO PODE MUDAR ORDEM
    MENSAGEM_FORUM, 
    MENSAGEM_CURSO_PROFESSOR, //Mensagem enviada pelo professor para o curso
    MENSAGEM_PRIVADA,
    MENSAGEM_GRUPO_FORUM,
    MENSAGEM_AUTOMATICA_NOVO_ARQUIVO,
    MENSAGEM_AUTOMATICA_NOVA_AGENDA
}