DOMStrings = {
    listInstructions: 'list_instructions'
};

const allInstructions = async () => {
    try{
    const response = await fetch(`http://localhost:8084/show-all-instructions`);
    if(response.ok) {
        //const jsonResponse = await response.json();
        console.log(`${typeof(response)} ${response}`);
    } throw new Error('Loading failed')
} catch (error) {
    console.log(error);
}
}
allInstructions();