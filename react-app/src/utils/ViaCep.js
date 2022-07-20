import { getJson } from "./Web"

const findCep = async (cep) => {
    return getJson(`https://viacep.com.br/ws/${cep}/json/`);
}

export default findCep;