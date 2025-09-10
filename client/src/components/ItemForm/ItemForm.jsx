const ItemForm = () => {
    return (
        <div className="item-form-container" style={{height:'100vh', overflowY: 'auto', overflowX:'hidden' }} >
            <div className="mx-2 mt-2">
                <div className="row">
                    <div className="card col-md-8 form-container">
                        <div className="card-body">
                            <form >
                                <div className="mb-3">
                                    <label htmlFor="image" className="form-label">
                                        <img src="https://placehold.co/48x48" alt="" width={48}/>
                                    </label>
                                    <input type="file" name="image" id="image" className='form-control' hidden />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">name</label>
                                    <input type="text"
                                           id="name"
                                           className="form-control"
                                           placeholder="Item Name"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" htmlFor="category">
                                        Category
                                    </label>
                                    <select name="category" id="category" className="form-control">
                                        <option value="">Select a category</option>
                                        <option value="category 1">category 1</option>
                                        <option value="category 2">category 2</option>
                                        <option value="category 3">category 3</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="price" className="form-label" >Price</label>
                                    <input type="number" name="price" id="price" className='form-control' placeholder="&#8377;200.00" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="description" className="form-label">Description</label>
                                    <textarea
                                        rows="5"
                                        name="description"
                                        id="description"
                                        className="form-control"
                                        placeholder="Write a description">

                                </textarea>
                                </div>

                                <button type="submit" className="btn btn-warning w-100" >save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
export default ItemForm;