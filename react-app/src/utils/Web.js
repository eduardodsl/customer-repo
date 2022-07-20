/**
 * fetches json data from a request
 * @param {string} url - url to fetch
 * @param {function} onFail - callback function if the request fails
 * @returns {object}
 * usage:
 * ```javascript
 * getJson('https://site.com.br', error => handleMyError(error));
 * ```
 * or:
 * ```javascript
 * try {
 *  getJson('https://site.com.br');
 * }catch (e) {
 *  handleMyError(e);
 * }
 * ```
 */
export const getJson = async (url, onFail) => {
    return requestJson(url, 'GET', {}, onFail);
};

export const postJson = async(url, data, onFail) => {
    return requestJson(url, 'POST', data, onFail);
}

export const putJson = async(url, data, onFail) => {
    return requestJson(url, 'PUT', data, onFail);
}

export const deleteJson = async(url, onFail) => {
    return requestJson(url, 'DELETE', {}, onFail);
}

export const requestJson = async(url, method = 'GET', body = {}, onFail) => {
    const params = { method };
    
    if(['POST', 'PUT'].includes(method)) params.body = body;
    
    try {
        const res = await fetch(url, params);
        return res.json();
    } catch (e) {
        if(onFail){
            return onFail(e);
        }else{
            throw e;
        }
    }
}